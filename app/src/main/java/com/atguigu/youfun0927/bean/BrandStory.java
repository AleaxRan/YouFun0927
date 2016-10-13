package com.atguigu.youfun0927.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class BrandStory {


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
        private String id;
        private String temp_id;
        private String english_name;
        private String logo_img;
        private String brand_code;
        private String ename;
        private String cname;
        private String first_letter;
        private String pic_img;
        private String story;
        private int look_num;
        private String brandStoryUrl;
        private String youfan_img;
        private String little_img;
        private String story_img;
        private int is_love;

        private List<TabBean> tab;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTemp_id() {
            return temp_id;
        }

        public void setTemp_id(String temp_id) {
            this.temp_id = temp_id;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }

        public String getLogo_img() {
            return logo_img;
        }

        public void setLogo_img(String logo_img) {
            this.logo_img = logo_img;
        }

        public String getBrand_code() {
            return brand_code;
        }

        public void setBrand_code(String brand_code) {
            this.brand_code = brand_code;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getFirst_letter() {
            return first_letter;
        }

        public void setFirst_letter(String first_letter) {
            this.first_letter = first_letter;
        }

        public String getPic_img() {
            return pic_img;
        }

        public void setPic_img(String pic_img) {
            this.pic_img = pic_img;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public int getLook_num() {
            return look_num;
        }

        public void setLook_num(int look_num) {
            this.look_num = look_num;
        }

        public String getBrandStoryUrl() {
            return brandStoryUrl;
        }

        public void setBrandStoryUrl(String brandStoryUrl) {
            this.brandStoryUrl = brandStoryUrl;
        }

        public String getYoufan_img() {
            return youfan_img;
        }

        public void setYoufan_img(String youfan_img) {
            this.youfan_img = youfan_img;
        }

        public String getLittle_img() {
            return little_img;
        }

        public void setLittle_img(String little_img) {
            this.little_img = little_img;
        }

        public String getStory_img() {
            return story_img;
        }

        public void setStory_img(String story_img) {
            this.story_img = story_img;
        }

        public int getIs_love() {
            return is_love;
        }

        public void setIs_love(int is_love) {
            this.is_love = is_love;
        }

        public List<TabBean> getTab() {
            return tab;
        }

        public void setTab(List<TabBean> tab) {
            this.tab = tab;
        }

        public static class TabBean {
            private int id;
            private String key;
            private String img;
            private Object home_url;
            private String story_url;
            private boolean is_choose;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getHome_url() {
                return home_url;
            }

            public void setHome_url(Object home_url) {
                this.home_url = home_url;
            }

            public String getStory_url() {
                return story_url;
            }

            public void setStory_url(String story_url) {
                this.story_url = story_url;
            }

            public boolean isIs_choose() {
                return is_choose;
            }

            public void setIs_choose(boolean is_choose) {
                this.is_choose = is_choose;
            }
        }
    }
}
