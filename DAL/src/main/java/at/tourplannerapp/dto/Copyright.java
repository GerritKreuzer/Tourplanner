package at.tourplannerapp.dto;

public class Copyright {
    private String text;
    private String imageUrl;
    private String imageAltText;

    public String getText() { return text; }
    public void setText(String value) { this.text = value; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String value) { this.imageUrl = value; }

    public String getImageAltText() { return imageAltText; }
    public void setImageAltText(String value) { this.imageAltText = value; }
}
