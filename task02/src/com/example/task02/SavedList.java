package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.jdeprscan.DeprDB.loadFromFile;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> list;
    public SavedList(File file) {
        this.file = file;
        this.list = new ArrayList<>();
        ReadFromFile();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E setted = list.set(index, element);
        WhriteToFile();
        return setted;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        WhriteToFile();
    }

    @Override
    public E remove(int index) {
        E removed = list.remove(index);
        WhriteToFile();
        return removed;
    }
    private void ReadFromFile() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                list = (List<E>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void WhriteToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
