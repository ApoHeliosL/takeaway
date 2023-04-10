package com.lili.takeaway.dto;

import com.lili.takeaway.entity.Setmeal;
import com.lili.takeaway.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
