package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 新增分类
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result add(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类:{}", categoryDTO);
        categoryService.add(categoryDTO);
        return Result.success();
    }

    /**
     * 分页查询分类列表
     */
    @GetMapping("/page")
    @ApiOperation("分页查询分类列表")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询分类列表:{}", categoryPageQueryDTO);
        PageResult result = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(result);
    }

    /**
     * 修改分类
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 启用禁用分类
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result changeStatus(@RequestParam Long id, @PathVariable Integer status) {
        log.info("启用禁用分类id:{}, 状态：{}", id, status);
        categoryService.changeStatus(id, status);
        return Result.success("修改成功");
    }

    /**
     * 删除分类
     */
    @DeleteMapping
    @ApiOperation("删除分类")
    public Result delete(Long id) {
        log.info("删除分类：{}", id);
        categoryService.delete(id);
        return Result.success("删除成功");
    }
}
