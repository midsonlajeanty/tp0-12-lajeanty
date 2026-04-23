package edu.mbds.tp012lajeanty.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.time.Duration;
import java.util.Locale;


@Dependent
public class Modificateur implements Serializable {

    public String modifier(String question, String roleSysteme, int numero, Duration depuisPrecedente) {
        StringBuilder sb = new StringBuilder();

        if (roleSysteme != null) {
            sb.append("[Rôle système activé]\n");
            sb.append(roleSysteme.toUpperCase(Locale.FRENCH).strip()).append("\n\n");
        }

        sb.append("==== Réponse n°").append(numero).append(" ====\n");

        if (depuisPrecedente == null) {
            sb.append("Première question de la session.\n");
        } else {
            sb.append("Temps écoulé depuis la question précédente : ")
                    .append(formaterDuree(depuisPrecedente)).append(".\n");
        }

        sb.append("Statistiques : ")
              .append(question.length()).append(" caractères, ")
              .append(compterMots(question)).append(" mots, ")
              .append("\n");

        sb.append("Question reçue : « ").append(question.strip()).append(" »");

        return sb.toString();
    }

    private static String formaterDuree(Duration d) {
        long secondes = Math.max(0, d.toSeconds());

        if (secondes < 60) {
            return secondes + " s";
        }

        long minutes = secondes / 60;

        long resteSecondes = secondes % 60;

        return minutes + " min " + resteSecondes + " s";
    }

    private static int compterMots(String s) {
        String trim = s.strip();

        if (trim.isEmpty()) {
            return 0;
        }

        return trim.split("\\s+").length;
    }
}