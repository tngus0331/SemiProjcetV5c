package suhyun.spring.mvc.vo;

public class ZipcodeVO {
    private String zipcode;
    private String sido;
    private String gugun;
    private String dong;
    private String ri;
    private String bunji;

    public ZipcodeVO() {
    }

    public ZipcodeVO(String zipcode, String sido, String gugun, String dong, String ri, String bunji) {
        this.zipcode = zipcode;
        this.sido = sido;
        this.gugun = gugun;
        this.dong = dong;
        this.ri = ri;
        this.bunji = bunji;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getGugun() {
        return gugun;
    }

    public void setGugun(String gugun) {
        this.gugun = gugun;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }

    public String getBunji() {
        return bunji;
    }

    public void setBunji(String bunji) {
        this.bunji = bunji;
    }
};