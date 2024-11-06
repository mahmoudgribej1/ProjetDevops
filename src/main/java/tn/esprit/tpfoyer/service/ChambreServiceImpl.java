package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;

    public List<Chambre> retrieveAllChambres() {
        log.info("In Method retrieveAllChambres: Fetching all chambres");
        List<Chambre> listC = chambreRepository.findAll();
        log.info("Out of retrieveAllChambres: Number of chambres retrieved = {}", listC.size());
        return listC;
    }

    public Chambre retrieveChambre(Long chambreId) {
        log.info("Retrieving chambre with ID: {}", chambreId);
        Chambre c = chambreRepository.findById(chambreId).orElse(null);
        if (c == null) {
            log.error("Chambre with ID: {} not found.", chambreId);
        } else {
            log.debug("Chambre retrieved: {}", c);
        }
        return c;
    }

    public Chambre addChambre(Chambre c) {
        log.info("Adding a new chambre: {}", c);
        Chambre chambre = chambreRepository.save(c);
        log.info("Chambre added successfully with ID: {}", chambre.getIdChambre());
        return chambre;
    }

    public Chambre modifyChambre(Chambre c) {
        log.info("Modifying chambre with ID: {}", c.getIdChambre());
        Chambre chambre = chambreRepository.save(c);
        log.info("Chambre modified successfully: {}", chambre);
        return chambre;
    }

    public void removeChambre(Long chambreId) {
        log.info("Removing chambre with ID: {}", chambreId);
        try {
            chambreRepository.deleteById(chambreId);
            log.info("Chambre removed successfully.");
        } catch (Exception e) {
            log.error("Error occurred while removing chambre with ID: {}", chambreId, e);
        }
    }

    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc) {
        log.info("Fetching chambres of type: {}", tc);
        List<Chambre> chambres = chambreRepository.findAllByTypeC(tc);
        log.info("Number of chambres found of type {}: {}", tc, chambres.size());
        return chambres;
    }

    public Chambre trouverchambreSelonEtudiant(long cin) {
        log.info("Searching chambre for student with CIN: {}", cin);
        Chambre chambre = chambreRepository.trouverChselonEt(cin);
        if (chambre != null) {
            log.info("Chambre found for student with CIN {}: {}", cin, chambre);
        } else {
            log.warn("No chambre found for student with CIN: {}", cin);
        }
        return chambre;
    }
}
