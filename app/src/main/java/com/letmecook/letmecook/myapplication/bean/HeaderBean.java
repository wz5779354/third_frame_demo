package com.letmecook.letmecook.myapplication.bean;

import java.util.List;

public class HeaderBean {

    private List<BannerBean> banners;
    private ComboAdvertisement comboAdvertisement;
    private ComboPopup comboPopup;

    public ComboPopup getComboPopup() {
        return comboPopup;
    }

    public void setComboPopup(ComboPopup comboPopup) {
        this.comboPopup = comboPopup;
    }

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public ComboAdvertisement getComboAdvertisement() {
        return comboAdvertisement;
    }

    public void setComboAdvertisement(ComboAdvertisement comboAdvertisement) {
        this.comboAdvertisement = comboAdvertisement;
    }



    public class ComboAdvertisement{

        private String id;
        private String url;
        private String introduction;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }


    }
    public class ComboPopup{
        private String id;
        private String url;
        private String introduction;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }

}
