package com.it.project.wallet.entity;

public class Wallet {
    private Long walletId;

    private Long walletUserid;

    private Long walletRemainsum;

    private Long deposit;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getWalletUserid() {
        return walletUserid;
    }

    public void setWalletUserid(Long walletUserid) {
        this.walletUserid = walletUserid;
    }

    public Long getWalletRemainsum() {
        return walletRemainsum;
    }

    public void setWalletRemainsum(Long walletRemainsum) {
        this.walletRemainsum = walletRemainsum;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }
}