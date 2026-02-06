package br.com.dao;

import br.com.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private long nextId = 1;
    private final List<UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model) {
        model.setId(nextId++);
        models.add(model);
        return model;
    }

    public UserModel update(final UserModel model) {
        var toUpdate = findBynaId(model.getId());
        // Em listas, é melhor substituir pelo índice para manter a posição
        int index = models.indexOf(toUpdate);
        models.set(index, model);
        return model;
    }

    public void delete(final long id) {
        var toDelete = findBynaId(id);
        models.remove(toDelete);
    }

    public List<UserModel> findAll() {
        return new ArrayList<>(models); // Retorna uma cópia por segurança
    }

    public UserModel findBynaId(final long id){
        return models.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

    }
}