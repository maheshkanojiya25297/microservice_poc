package ExelFileUploadApachePoI.entities;

import lombok.*;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "Product")
public class Product {

    @Id
    private Integer productId;
    private String productName;
    private String productDesc;
    private String ProductPrice;
}
