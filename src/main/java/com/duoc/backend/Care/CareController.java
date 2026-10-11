package com.duoc.backend.care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/care")
public class CareController {

    @Autowired
    private CareRepository careRepository;

    @GetMapping
    public List<Care> getAllCares() {
        return (List<Care>) careRepository.findAll();
    }

    @GetMapping("/{id}")
    public Care getCareById(@PathVariable Long id) {
        return careRepository.findById(id).orElse(null);
    }

    @PostMapping
public Care saveCare(@RequestBody CareDTO dto) {

    Care care = new Care();

    care.setName(dto.getName());
    care.setCost(dto.getCost());

    return careRepository.save(care);
}
    @DeleteMapping("/{id}")
    public void deleteCare(@PathVariable Long id) {
        careRepository.deleteById(id);
    }
}