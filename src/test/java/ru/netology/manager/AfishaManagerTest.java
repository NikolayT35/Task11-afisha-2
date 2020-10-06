package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Film;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class AfishaManagerTest {
    @Mock
    private AfishaRepository repository;
    @InjectMocks
    private AfishaManager manager;

    Film first = new Film(1, "Bloodshot", "action");
    Film second = new Film(2, "Onward", "cartoon");
    Film third = new Film(3, "Hotel Belgrade", "comedy");
    Film fourth = new Film(4, "The Gentlemen", "action");
    Film fifth = new Film(5, "The Invisible Man", "horror");
    Film sixth = new Film(6, "Trolls World Tour", "cartoon");
    Film seventh = new Film(7, "Number One", "comedy");
    Film eighth = new Film(8, "Argument", "action");
    Film ninth = new Film(9, "Greenland", "action");
    Film tenth = new Film(10, "Mulan", "drama");
    Film eleventh = new Film(11, "Space dogs 3", "cartoon");
    Film twelfth = new Film(12, "La Gallina Turuleca", "cartoon");

    @Test
    public void shouldAdd() {

        Film[] returned = new Film[]{first, second, third};
        doReturn(returned).when(repository).findAll();

        Film[] actual = manager.getLastAddedItems();
        Film[] expected = new Film[]{third, second, first};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddMoreThan10() {

        Film[] returned = new Film[]
                {first, second, third, fourth, fifth, sixth, seventh, eighth,
                        ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();

        Film[] actual = manager.getLastAddedItems();
        Film[] expected = new Film[]{eleventh, tenth, ninth, eighth, seventh, sixth,
                fifth, fourth, third, second};

        assertArrayEquals(expected, actual);
    }

    @Test
    void getAfishaEmpty() {

        Film[] returned = new Film[]{};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).save(first);

        manager.add(first);
        Film[] actual = manager.getLastAddedItems();
        Film[] expected = new Film[]{};

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldShowLessThan10() {
        manager = new AfishaManager(repository, 5);
        Film[] returned = new Film[]
                {first, second, third, fourth, fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();
        Film[] actual = manager.getLastAddedItems();
        Film[] expected = new Film[]{seventh, sixth, fifth, fourth,third};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowMinus() {
        manager = new AfishaManager(repository, -1);
        Film[] returned = new Film[]
                {first, second, third};
        doReturn(returned).when(repository).findAll();
        Film[] actual = manager.getLastAddedItems();
        Film[] expected = new Film[]{};

        assertArrayEquals(expected, actual);
    }
}