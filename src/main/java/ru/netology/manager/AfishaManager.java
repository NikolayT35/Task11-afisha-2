package ru.netology.manager;

import ru.netology.domain.Film;
import ru.netology.repository.AfishaRepository;

public class AfishaManager {
    private AfishaRepository repository;
    int itemsToShow = 10;

    public AfishaManager() {
    }

    public AfishaManager(AfishaRepository repository, int itemsToShow) {
        this.repository = repository;
        this.itemsToShow = itemsToShow;
    }

    public void add(Film item) {
        repository.save(item);
    }
    public Film[] getLastAddedItems() {
        int length = itemsToShow;

        Film[] items = repository.findAll();

        if (itemsToShow < 0) {
            length = 0;
        }
        if (itemsToShow > items.length) {
            length = items.length;
        }

        Film[] result = new Film[length];

        for (int i = 0; i < length; i++) {
            int index = items.length - i - 1;
            result[i] = items[index];
        }
        return result;
    }
}