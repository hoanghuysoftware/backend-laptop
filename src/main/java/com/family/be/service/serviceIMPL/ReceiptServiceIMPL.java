package com.family.be.service.serviceIMPL;

import com.family.be.dto.request.ProductRequest;
import com.family.be.dto.request.ReceiptRequest;
import com.family.be.models.*;
import com.family.be.repository.*;
import com.family.be.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReceiptServiceIMPL implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ProductRepository productRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final ImporterRepository importerRepository;
    private final BrandRepository brandRepository;

    @Override
    public Receipt createNewReceiptForProductExists(ReceiptRequest receiptRequest) {
        Importer importer = importerRepository.findImporterById(receiptRequest.getImporterId()).orElseThrow(
                () -> new RuntimeException("Not found importer has id: " + receiptRequest.getImporterId()));

        Receipt receipt = Receipt.builder()
                .importer(importer)
                .dateImport(new Date())
                .build();

        Set<DetailReceipt> detailReceiptSet = new HashSet<>();
        receiptRequest.getDetails().forEach(item -> {
            Product product = productRepository.getProductById(item.getProductId()).orElseThrow(
                    () -> new RuntimeException("Not found product !"));

            product.setPriceProduct(item.getPriceImport() + 2000000);
            product.setQuantityProduct(product.getQuantityProduct() + item.getQuantityImport());
            detailReceiptSet.add(DetailReceipt.builder()
                    .receipt(receipt)
                    .product(product)
                    .priceDetailReceipt(item.getPriceImport())
                    .quantityDetailReceipt(item.getQuantityImport())
                    .build());
        });
        receipt.setDetailReceipts(detailReceiptSet);
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt createNewReceiptForProductNew(ReceiptRequest receiptRequest) {
        Importer importer = importerRepository.findImporterById(receiptRequest.getImporterId()).orElseThrow(
                () -> new RuntimeException("Not found importer has id: " + receiptRequest.getImporterId()));

        Receipt receipt = Receipt.builder()
                .importer(importer)
                .dateImport(new Date())
                .build();

        Set<DetailReceipt> detailReceiptSet = new HashSet<>();
        receiptRequest.getDetails().forEach(item -> {
            Product product = createProduct(item.getProductRequest());

            product.setPriceProduct(item.getPriceImport() + 2000000);
            product.setQuantityProduct(product.getQuantityProduct() + item.getQuantityImport());
            detailReceiptSet.add(DetailReceipt.builder()
                    .receipt(receipt)
                    .product(product)
                    .priceDetailReceipt(item.getPriceImport())
                    .quantityDetailReceipt(item.getQuantityImport())
                    .build());
        });
        receipt.setDetailReceipts(detailReceiptSet);
        return receiptRepository.save(receipt);
    }

    public Product createProduct(ProductRequest productRequest){
        // init product
        Product product = Product.builder()
                .nameProduct(productRequest.getNameProduct())
                .cpuProduct(productRequest.getCpuProduct())
                .ramProduct(productRequest.getRamProduct())
                .screenProduct(productRequest.getScreenProduct())
                .romProduct(productRequest.getRomProduct())
                .cardProduct(productRequest.getCardProduct())
                .descProduct(productRequest.getDescProduct())
                .priceProduct(productRequest.getPriceProduct())
                .quantityProduct(productRequest.getQuantityProduct())
                .build();

        // find and add set product attribute into product
        Set<ProductAttribute> productAttributeSet = new HashSet<>();
        productRequest.getAttributeIds().forEach(item -> {
            ProductAttribute productAttribute = productAttributeRepository.findProductAttributeById(item).orElseThrow(
                    () -> new RuntimeException("Not found product attribute !")
            );
            productAttributeSet.add(productAttribute);
        });
        product.setProductAttributes(productAttributeSet);

        // find and add brand into product
        Brand brand = brandRepository.findBrandById(productRequest.getBrandId()).orElseThrow(
                () -> new RuntimeException("Not found brand !")
        );
        product.setBrand(brand);

        // find and add images into product
        Set<Image> imageSet = new HashSet<>();
        productRequest.getImages().forEach(image -> {
            Image img = Image.builder()
                    .product(product)
                    .imageData(image)
                    .build();
            imageSet.add(img);
        });
        product.setImages(imageSet);

        return productRepository.save(product);
    }

    @Override
    public List<Receipt> getAllReceipt() {
        return receiptRepository.findAll();
    }
}
