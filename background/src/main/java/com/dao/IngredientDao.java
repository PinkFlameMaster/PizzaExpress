package com.dao;

import com.dto.IngredientDto;
import com.pojo.Ingredient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> getIngredientByOrderItem(int orderId);
    List<IngredientDto> getAllIngredientDto(int factoryId);
    List<IngredientDto> getIngredientByType(@Param("type")String type, @Param("factoryId")int factoryId);
    void importIngredient(@Param("type")String type, @Param("source")String source,
                          @Param("amount")float amount,@Param("importDate")String importDate,@Param("factoryId")int factoryId);
    List<Ingredient> setOrderStockValue(int id);
    void setNewAmount(@Param("id")int id, @Param("newAmount") float newAmount);
    void newOrderIngredient(@Param("orderItemId")int oderItemId,@Param("ingredientId")int ingredientId);
    List<Integer> getOrderItemByOrderId(int orderId);
}
