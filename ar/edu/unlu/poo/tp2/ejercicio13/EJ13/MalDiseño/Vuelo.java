package EJ13.MalDiseño;

import java.util.ArrayList;

public class Vuelo {
    private final ArrayList<Persona> personas = new ArrayList<>();

    public void add(Persona persona) {
        personas.add(persona);
    }
}
