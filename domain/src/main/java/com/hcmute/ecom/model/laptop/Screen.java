package com.hcmute.ecom.model.laptop;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Screen {
    private Long id;
    private Float size; // x inches
    private String technology;
    private String resolution;
    private String type;
    private Float refresh_rate;
    private String panel;
    private Integer brightness;
    private String color_coverage;
    private Boolean has_touch_screen;

    public Screen(Long id, Float size, String technology, String resolution, String type, Float refresh_rate,
                  String panel, Integer brightness, String color_coverage, Boolean has_touch_screen) {
        this.id = id;
        this.size = size;
        this.technology = technology;
        this.resolution = resolution;
        this.type = type;
        this.refresh_rate = refresh_rate;
        this.panel = panel;
        this.brightness = brightness;
        this.color_coverage = color_coverage;
        this.has_touch_screen = has_touch_screen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getRefreshRate() {
        return refresh_rate;
    }

    public void setRefreshRate(Float refresh_rate) {
        this.refresh_rate = refresh_rate;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public String getColorCoverage() {
        return color_coverage;
    }

    public void setColorCoverage(String color_coverage) {
        this.color_coverage = color_coverage;
    }

    public Boolean getHasTouchScreen() {
        return has_touch_screen;
    }

    public void setHasTouchScreen(Boolean has_touch_screen) {
        this.has_touch_screen = has_touch_screen;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", size=" + size +
                ", technology='" + technology + '\'' +
                ", resolution='" + resolution + '\'' +
                ", type='" + type + '\'' +
                ", refresh_rate=" + refresh_rate +
                ", panel='" + panel + '\'' +
                ", brightness=" + brightness +
                ", color_coverage='" + color_coverage + '\'' +
                ", has_touch_screen=" + has_touch_screen +
                '}';
    }
}
