package ptit.QLKS.controller.impl;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.controller.ThemeController;
import ptit.QLKS.entity.Theme;
import ptit.QLKS.repository.ThemeRepository;

import java.util.List;

@RestController
@Log4j2
public class ThemeControllerImpl implements ThemeController {

    @Autowired
    ThemeRepository themeRepository;

    @Override
    public ResponseEntity<?> createTheme(String link) {
        try {
            if(link != null || link != ""){
                Theme theme = new Theme();
                theme.setImage(link);
                themeRepository.save(theme);
                return ResponseEntity.ok(link);
            }
            return null;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteTheme(int id) {
        try {
            themeRepository.deleteById(id);
            return ResponseEntity.ok(Constrant.SUCCESS);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllTheme() {
        try {
            List<Theme> list = themeRepository.findAll();
            return ResponseEntity.ok(list);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
