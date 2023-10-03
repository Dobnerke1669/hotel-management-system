package ro.fortech.bigproject.entities;

import jakarta.persistence.*;

@Entity
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @OneToOne(mappedBy = "image")
    private Product product;

    @Lob
    @Column(length = 1000)
    private byte[] imageData;

    public ImageData(String name, String type, Product product, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.product = product;
        this.imageData = imageData;
    }
    public ImageData()
    {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Product getProduct() {
        return product;
    }

    public byte[] getImageData() {
        return imageData;
    }


}
