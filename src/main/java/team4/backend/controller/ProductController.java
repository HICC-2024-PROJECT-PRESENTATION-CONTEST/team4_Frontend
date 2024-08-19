package team4.backend.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team4.backend.dto.ProductResponseDto;
import team4.backend.entity.Product;
import team4.backend.response.ApiResponse;
import team4.backend.service.ProductService;
import team4.backend.service.UserDetailsServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;


  // 테스트 하려고 넣음 실제로는 파이썬에서 db에 제품 정보 넣을 듯
  @PostMapping("/create")
  public ResponseEntity<?> createProduct(@RequestBody Product product){
    productService.createProduct(product);
    return ResponseEntity.ok(ApiResponse.ok("제품 정보 생성 성공",null));
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchProducts(@RequestParam(name = "query") String query) {
    List<ProductResponseDto> productResponseDtoList = productService.searchProducts(query);
    return ResponseEntity.ok(ApiResponse.ok("제품 검색 성공",productResponseDtoList));
  }

  @GetMapping("/search/{productId}")
  public ResponseEntity<?> searchProduct(@PathVariable Long productId) {
    ProductResponseDto productResponseDto = productService.searchProduct(productId);
    return ResponseEntity.ok(ApiResponse.ok("제품 세부사항 검색 성공",productResponseDto));
  }

}
