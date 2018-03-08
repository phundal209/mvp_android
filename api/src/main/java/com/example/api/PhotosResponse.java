package com.example.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phundal2091 on 2/2/18.
 */

public class PhotosResponse {

    @Expose
    @SerializedName("stat")
    private String stat;
    @Expose
    @SerializedName("photos")
    private PhotosEntity photos;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public PhotosEntity getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosEntity photos) {
        this.photos = photos;
    }

    public static class PhotosEntity {
        @Expose
        @SerializedName("photo")
        private List<PhotoEntity> photo;
        @Expose
        @SerializedName("total")
        private String total;
        @Expose
        @SerializedName("perpage")
        private int perpage;
        @Expose
        @SerializedName("pages")
        private int pages;
        @Expose
        @SerializedName("page")
        private int page;

        public List<PhotoEntity> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoEntity> photo) {
            this.photo = photo;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPerpage() {
            return perpage;
        }

        public void setPerpage(int perpage) {
            this.perpage = perpage;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

    public static class PhotoEntity {
        @Expose
        @SerializedName("isfamily")
        private int isfamily;
        @Expose
        @SerializedName("isfriend")
        private int isfriend;
        @Expose
        @SerializedName("ispublic")
        private int ispublic;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("farm")
        private int farm;
        @Expose
        @SerializedName("server")
        private String server;
        @Expose
        @SerializedName("secret")
        private String secret;
        @Expose
        @SerializedName("owner")
        private String owner;
        @Expose
        @SerializedName("id")
        private String id;

        public int getIsfamily() {
            return isfamily;
        }

        public void setIsfamily(int isfamily) {
            this.isfamily = isfamily;
        }

        public int getIsfriend() {
            return isfriend;
        }

        public void setIsfriend(int isfriend) {
            this.isfriend = isfriend;
        }

        public int getIspublic() {
            return ispublic;
        }

        public void setIspublic(int ispublic) {
            this.ispublic = ispublic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFarm() {
            return farm;
        }

        public void setFarm(int farm) {
            this.farm = farm;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
