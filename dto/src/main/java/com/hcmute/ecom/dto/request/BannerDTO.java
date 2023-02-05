package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.Banner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * @version 1.0.2
 * */
@ApiModel("Class representing for Banner request body")
public class BannerDTO {
    private String path;
    private String type;
    @ApiModelProperty(example = "2022-12-22")
    private String usedDate;
    @ApiModelProperty(example = "2022-12-22")
    private String endedDate;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }

    public String getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(String endedDate) {
        this.endedDate = endedDate;
    }

    public static Banner transform(BannerDTO bannerDTO) {
        Banner banner = new Banner();
        banner.setPath(bannerDTO.getPath());
        banner.setType(bannerDTO.getType());
        banner.setUsedDate(Date.valueOf(bannerDTO.getUsedDate()));
        banner.setEndedDate(Date.valueOf(bannerDTO.getEndedDate()));
        return banner;
    }
}
