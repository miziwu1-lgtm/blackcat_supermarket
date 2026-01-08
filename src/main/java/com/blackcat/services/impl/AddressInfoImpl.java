package com.blackcat.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackcat.entity.AddressInfo;
import com.blackcat.dao.AddressInfoMapper;
import com.blackcat.services.IAddressInfoService;
import org.springframework.stereotype.Service;

@Service
public class AddressInfoImpl
        extends ServiceImpl<AddressInfoMapper, AddressInfo>
        implements IAddressInfoService {

}