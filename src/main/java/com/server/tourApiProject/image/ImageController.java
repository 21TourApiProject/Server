package com.server.tourApiProject.image;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"4.1 이미지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @ApiOperation(value = "모든 이미지 조회", notes = "모든 이미지를 조회한다")
    @GetMapping(value = "images")
    public List<Image> getImage(){ return imageService.getAllImage(); }

    @ApiOperation(value = "이미지 입력", notes = "이미지 정보를 입력한다")
    @PostMapping(value = "image")
    public void createImage(@RequestBody Image image){
        imageService.createImage(image);
    }

}
