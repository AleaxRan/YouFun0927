package com.atguigu.youfun0927.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class GoodDetailBean {

    private DataBean data;
    private String info;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private ClsInfoBean clsInfo;
        private int isFavorite;
        private BrandDispatchGoodsBean brandDispatchGoods;
        private IntegralBean integral;
        private int commentCount;
        private String avgComment;
        private boolean isCanUseVoucher;
        private String voucherTip;
        private List<ProPicUrlBean> proPicUrl;
        private List<ClsPicUrlBean> clsPicUrl;
        private List<ActivityBean> activity;
        private List<?> parameters;
        private List<?> addPicUrl;
        private List<?> sizeTable;

        public ClsInfoBean getClsInfo() {
            return clsInfo;
        }

        public void setClsInfo(ClsInfoBean clsInfo) {
            this.clsInfo = clsInfo;
        }

        public int getIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(int isFavorite) {
            this.isFavorite = isFavorite;
        }

        public BrandDispatchGoodsBean getBrandDispatchGoods() {
            return brandDispatchGoods;
        }

        public void setBrandDispatchGoods(BrandDispatchGoodsBean brandDispatchGoods) {
            this.brandDispatchGoods = brandDispatchGoods;
        }

        public IntegralBean getIntegral() {
            return integral;
        }

        public void setIntegral(IntegralBean integral) {
            this.integral = integral;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getAvgComment() {
            return avgComment;
        }

        public void setAvgComment(String avgComment) {
            this.avgComment = avgComment;
        }

        public boolean isIsCanUseVoucher() {
            return isCanUseVoucher;
        }

        public void setIsCanUseVoucher(boolean isCanUseVoucher) {
            this.isCanUseVoucher = isCanUseVoucher;
        }

        public String getVoucherTip() {
            return voucherTip;
        }

        public void setVoucherTip(String voucherTip) {
            this.voucherTip = voucherTip;
        }

        public List<ProPicUrlBean> getProPicUrl() {
            return proPicUrl;
        }

        public void setProPicUrl(List<ProPicUrlBean> proPicUrl) {
            this.proPicUrl = proPicUrl;
        }

        public List<ClsPicUrlBean> getClsPicUrl() {
            return clsPicUrl;
        }

        public void setClsPicUrl(List<ClsPicUrlBean> clsPicUrl) {
            this.clsPicUrl = clsPicUrl;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public List<?> getParameters() {
            return parameters;
        }

        public void setParameters(List<?> parameters) {
            this.parameters = parameters;
        }

        public List<?> getAddPicUrl() {
            return addPicUrl;
        }

        public void setAddPicUrl(List<?> addPicUrl) {
            this.addPicUrl = addPicUrl;
        }

        public List<?> getSizeTable() {
            return sizeTable;
        }

        public void setSizeTable(List<?> sizeTable) {
            this.sizeTable = sizeTable;
        }

        public static class ClsInfoBean {
            private String code;
            private String name;
            private String marketPrice;
            private String sale_price;
            private String brand;
            private String brandId;
            private String brandUrl;
            private String showName;
            private int stockCount;
            private String statusname;
            private String mainImage;
            private String description;
            private int status;
            private String collocationShoppingIcon;
            private String collocationShoppingEndTime;
            private int isNoSale;
            private int isWish;
            private String normalActivityIcon;
            private String normalActivityCutdown;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(String marketPrice) {
                this.marketPrice = marketPrice;
            }

            public String getSale_price() {
                return sale_price;
            }

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getBrandId() {
                return brandId;
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public String getBrandUrl() {
                return brandUrl;
            }

            public void setBrandUrl(String brandUrl) {
                this.brandUrl = brandUrl;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public int getStockCount() {
                return stockCount;
            }

            public void setStockCount(int stockCount) {
                this.stockCount = stockCount;
            }

            public String getStatusname() {
                return statusname;
            }

            public void setStatusname(String statusname) {
                this.statusname = statusname;
            }

            public String getMainImage() {
                return mainImage;
            }

            public void setMainImage(String mainImage) {
                this.mainImage = mainImage;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCollocationShoppingIcon() {
                return collocationShoppingIcon;
            }

            public void setCollocationShoppingIcon(String collocationShoppingIcon) {
                this.collocationShoppingIcon = collocationShoppingIcon;
            }

            public String getCollocationShoppingEndTime() {
                return collocationShoppingEndTime;
            }

            public void setCollocationShoppingEndTime(String collocationShoppingEndTime) {
                this.collocationShoppingEndTime = collocationShoppingEndTime;
            }

            public int getIsNoSale() {
                return isNoSale;
            }

            public void setIsNoSale(int isNoSale) {
                this.isNoSale = isNoSale;
            }

            public int getIsWish() {
                return isWish;
            }

            public void setIsWish(int isWish) {
                this.isWish = isWish;
            }

            public String getNormalActivityIcon() {
                return normalActivityIcon;
            }

            public void setNormalActivityIcon(String normalActivityIcon) {
                this.normalActivityIcon = normalActivityIcon;
            }

            public String getNormalActivityCutdown() {
                return normalActivityCutdown;
            }

            public void setNormalActivityCutdown(String normalActivityCutdown) {
                this.normalActivityCutdown = normalActivityCutdown;
            }
        }

        public static class BrandDispatchGoodsBean {
            private String img;
            private String url;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class IntegralBean {
            private String integralInfo;
            private int userLevel;
            private String userName;
            @SerializedName("double")
            private int doubleX;
            private String icon_content;

            public String getIntegralInfo() {
                return integralInfo;
            }

            public void setIntegralInfo(String integralInfo) {
                this.integralInfo = integralInfo;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(int userLevel) {
                this.userLevel = userLevel;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getDoubleX() {
                return doubleX;
            }

            public void setDoubleX(int doubleX) {
                this.doubleX = doubleX;
            }

            public String getIcon_content() {
                return icon_content;
            }

            public void setIcon_content(String icon_content) {
                this.icon_content = icon_content;
            }
        }

        public static class ProPicUrlBean {
            private String filE_PATH;
            private String remark;
            private int isMainImage;

            public String getFilE_PATH() {
                return filE_PATH;
            }

            public void setFilE_PATH(String filE_PATH) {
                this.filE_PATH = filE_PATH;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getIsMainImage() {
                return isMainImage;
            }

            public void setIsMainImage(int isMainImage) {
                this.isMainImage = isMainImage;
            }
        }

        public static class ClsPicUrlBean {
            private String filE_PATH;
            private String remark;
            private int isMainImage;

            public String getFilE_PATH() {
                return filE_PATH;
            }

            public void setFilE_PATH(String filE_PATH) {
                this.filE_PATH = filE_PATH;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getIsMainImage() {
                return isMainImage;
            }

            public void setIsMainImage(int isMainImage) {
                this.isMainImage = isMainImage;
            }
        }

        public static class ActivityBean {
            private String url;
            private String info;
            private String name;
            private String icon_content;
            private String aid;
            private String type;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon_content() {
                return icon_content;
            }

            public void setIcon_content(String icon_content) {
                this.icon_content = icon_content;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
