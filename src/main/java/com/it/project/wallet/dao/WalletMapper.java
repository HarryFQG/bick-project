package com.it.project.wallet.dao;

import com.it.project.wallet.entity.Wallet;

import java.util.List;

import com.it.project.wallet.entity.WalletExample;
import org.apache.ibatis.annotations.Param;

public interface WalletMapper {
    int countByExample(WalletExample example);

    int deleteByExample(WalletExample example);

    int deleteByPrimaryKey(Long walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    List<Wallet> selectByExample(WalletExample example);

    Wallet selectByPrimaryKey(Long walletId);

    int updateByExampleSelective(@Param("record") Wallet record, @Param("example") WalletExample example);

    int updateByExample(@Param("record") Wallet record, @Param("example") WalletExample example);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
    Wallet selectByUserId(int userId);
}