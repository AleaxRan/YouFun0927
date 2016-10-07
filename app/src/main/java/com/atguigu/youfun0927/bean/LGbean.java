package com.atguigu.youfun0927.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/6.
 */
public class LGbean {

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
        private int layoutType;
        private String total;

        private List<AttrBean> attr;

        private List<ListBean> list;

        public int getLayoutType() {
            return layoutType;
        }

        public void setLayoutType(int layoutType) {
            this.layoutType = layoutType;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<AttrBean> getAttr() {
            return attr;
        }

        public void setAttr(List<AttrBean> attr) {
            this.attr = attr;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AttrBean {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ListBean {
            private String id;
            private String title;
            private String img;
            private String url;
            private List<?> product_list;
            private List<?> keywords_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

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

            public List<?> getProduct_list() {
                return product_list;
            }

            public void setProduct_list(List<?> product_list) {
                this.product_list = product_list;
            }

            public List<?> getKeywords_list() {
                return keywords_list;
            }

            public void setKeywords_list(List<?> keywords_list) {
                this.keywords_list = keywords_list;
            }
        }
    }
}
