package com.blackcat.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.entity.ShoppingCart;
import com.blackcat.dao.ShoppingCartMapper;
import com.blackcat.services.IShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartImpl
        extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements IShoppingCartService {

}