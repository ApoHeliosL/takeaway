package com.lili.takeaway.controller;/*
 *@ClassName:DishController
 *@Description:
 *@Author:LL
 *@Date:2023/3/27
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lili.takeaway.common.R;
import com.lili.takeaway.dto.DishDto;
import com.lili.takeaway.entity.Category;
import com.lili.takeaway.entity.Dish;
import com.lili.takeaway.entity.DishFlavor;
import com.lili.takeaway.service.CateGoryService;
import com.lili.takeaway.service.DishFlavorService;
import com.lili.takeaway.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CateGoryService cateGoryService;

    /**
     * 新增分类
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody  DishDto dishDto){
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");

    }

    /**
     * 菜品分类 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //构造分页构造器
        Page<Dish> pageInfo = new Page<Dish>(page,pageSize);
        Page<DishDto> dishDtoPage = new Page<DishDto>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name!=null,Dish::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        dishService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        //处理records数据
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            //对象拷贝
            BeanUtils.copyProperties(item,dishDto);
            //获取分类id
            Long categoryId = item.getCategoryId();
            //根据id查对象
            Category category = cateGoryService.getById(categoryId);
            //获取分类名称
            String categoryName = category.getName();
            dishDto.setCategoryName(categoryName);

            return  dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);
    }

    /**
     * 根据id 查询菜品信息和对应口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    /**
     * 修改菜品信息 口味信息
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }

    /**
     *根据条件查询对应的菜品数据
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        //构造查询条件对象
        LambdaQueryWrapper<Dish>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus,1);//查询状态为1  表示在售
        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> dishList = dishService.list(queryWrapper);

        // 在原来的基础上追加flavor信息。
        // stream处理。
        List<DishDto> dishDtoList =
                dishList.stream()
                        .map(
                                (item) -> {
                                    DishDto dishDto = new DishDto();
                                    BeanUtils.copyProperties(item, dishDto);
                                    // 分类id
                                    Long categoryId = item.getCategoryId();
                                    // 根据id查分类对象。
                                    Category category = cateGoryService.getById(categoryId);
                                    // 获取categoryName
                                    if (category != null) {
                                        String categoryName = category.getName();
                                        dishDto.setCategoryName(categoryName);
                                    }
                                    // 在原来的基础上追加flavor信息。
                                    Long dishId = item.getId();
                                    LambdaQueryWrapper<DishFlavor> lambdaqw = new LambdaQueryWrapper<>();
                                    lambdaqw.eq(DishFlavor::getDishId,dishId);
                                    List<DishFlavor> dishFlavors = dishFlavorService.list(lambdaqw);
                                    dishDto.setFlavors(dishFlavors);
                                    return dishDto;
                                })
                        .collect(Collectors.toList());
        return R.success(dishDtoList);
    }

    /**
     * 根据id修改菜品的状态status(停售和起售)
     *
     *0停售，1起售。
     * @param status
     * @param
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatusById(@PathVariable Integer status, Long[] ids) {
        // 增加日志验证是否接收到前端参数。
        log.info("根据id修改菜品的状态:{},id为：{}", status, ids);
        // 通过id查询数据库。修改id为ids数组中的数据的菜品状态status为前端页面提交的status。
        for (int i = 0; i < ids.length; i++) {
            Long id=ids[i];
            //根据id得到每个dish菜品。
            Dish dish = dishService.getById(id);
            dish.setStatus(status);
            dishService.updateById(dish);
        }
        return R.success("修改菜品状态成功");
    }


    /**
     * 根据id删除一个或批量删除菜品。
     *
     * @param ids 待删除的菜品id。
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        // 增加日志验证是否接收到前端参数。
        log.info("根据id删除一个菜品:{}", ids);
        //执行删除。
        dishService.deleteWithFlavor(ids);
        return R.success("删除菜品成功");
    }


}
