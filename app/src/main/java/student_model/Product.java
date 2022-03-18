package student_model;

import android.net.Uri;
public class Product {
    private String name;
    private String  buying_price;
    private String  selling_pice;
    private Uri  img_url;

    public Product(String name, String buying_price, String selling_pice, Uri img_url) {
        this.name = name;
        this.buying_price = buying_price;
        this.selling_pice = selling_pice;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(String buying_price) {
        this.buying_price = buying_price;
    }

    public String getSelling_pice() {
        return selling_pice;
    }

    public void setSelling_pice(String selling_pice) {
        this.selling_pice = selling_pice;
    }

    public Uri getImg_url() {
        return img_url;
    }

    public void setImg_url(Uri img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", buying_price=" + buying_price +
                ", selling_pice=" + selling_pice +
                ", img_url=" + img_url +
                '}';
    }
}
