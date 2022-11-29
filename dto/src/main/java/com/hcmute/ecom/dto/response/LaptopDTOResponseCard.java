package com.hcmute.ecom.dto.response;

import com.hcmute.ecom.model.Discount;
import com.hcmute.ecom.model.laptop.GraphicCard;
import com.hcmute.ecom.model.laptop.HardDrive;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.model.laptop.Screen;

import java.math.BigDecimal;

/**
 * @author Nhat Phi
 * @since 2022-11-29
 * */
public class LaptopDTOResponseCard {
    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String image;
    private String cpuBrand;
    private String cpuType;
    private Byte ramCapacity;
    private HardDrive hardDrive;
    private GraphicCard graphicCard;
    private Screen screen;
    private Float ratingPoint;
    private Integer feedbackCount;

    public LaptopDTOResponseCard() {}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCpuBrand() {
        return cpuBrand;
    }

    public void setCpuBrand(String cpuBrand) {
        this.cpuBrand = cpuBrand;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public Byte getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(Byte ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public HardDrive getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(HardDrive hardDrive) {
        this.hardDrive = hardDrive;
    }

    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCard graphicCard) {
        this.graphicCard = graphicCard;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Float getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(Float ratingPoint) {
        this.ratingPoint = ratingPoint;
    }

    public Integer getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(Integer feedbackCount) {
        this.feedbackCount = feedbackCount;
    }

    public static LaptopDTOResponseCard getData(Laptop product, GraphicCard graphicCard, String imagePath,
                                                HardDrive hardDrive, Screen screen, Discount discount,
                                                float ratingPoint, int feedbackCount) {
        LaptopDTOResponseCard laptop = new LaptopDTOResponseCard();
        laptop.setId(product.getId());
        laptop.setName(product.getName());
        laptop.setImage(imagePath);
        laptop.setPrice(product.getPrice());

        if(discount != null) {
            BigDecimal discountPrice = laptop.getPrice().multiply(BigDecimal.valueOf(discount.getRate()));

            if(discountPrice.compareTo(discount.getMaxAmount()) > 0) {
                laptop.setDiscountPrice(
                        laptop.getPrice().subtract(discount.getMaxAmount())
                );
            }
            else {
                laptop.setDiscountPrice(
                        laptop.getPrice().subtract(discountPrice)
                );
            }
        }
        else {
            laptop.setDiscountPrice(product.getPrice());
        }

        laptop.setRamCapacity(product.getRamCapacity());
        laptop.setCpuBrand(product.getCpuBrand());
        laptop.setCpuType(product.getCpuType());
        laptop.setGraphicCard(graphicCard);
        laptop.setHardDrive(hardDrive);
        laptop.setScreen(screen);
        laptop.setRatingPoint(ratingPoint);
        laptop.setFeedbackCount(feedbackCount);
        return laptop;
    }
}
