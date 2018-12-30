package com.elegion.courserafirstcourseprogrammingtest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class CharacterCreator extends Observable implements Serializable {

    public enum Specialization {
        WARRIOR, ARCHER, MAGE
    }

    public enum Race {
        HUMAN, ELF, ORC, DWARF
    }

    public enum Attribute {
        STRENGTH, AGILITY, INTELLECT, STAMINA, LUCK
    }

    public enum Perk {
        BERSERK, CALM, LIGHTWEIGHT, HEAVYARMORED, OBSERVANT, MEDITATIONS
    }

    private String mName;
    private Specialization mSpecialization;
    private Race mRace;
    private int mAvailablePoints;

    private Map<String, Integer> mAttributesMap = new HashMap<>();
    private Map<String, Boolean> mPerksMap = new HashMap<>();


    public CharacterCreator() {
        mRace = Race.HUMAN;
        mSpecialization = Specialization.WARRIOR;
        mAvailablePoints = 5;
        mAttributesMap.put(Attribute.STRENGTH.name(), 5);
        mAttributesMap.put(Attribute.AGILITY.name(), 5);
        mAttributesMap.put(Attribute.INTELLECT.name(), 5);
        mAttributesMap.put(Attribute.STAMINA.name(), 5);
        mAttributesMap.put(Attribute.LUCK.name(), 5);
    }


    public String[] getSpecializations() {
        return new String[]{"Warrior", "Archer", "Mage"};
    }

    public void setSpecialization(int position) {
        switch (position) {
            case 0:
                mSpecialization = Specialization.WARRIOR;
                break;
            case 1:
                mSpecialization = Specialization.ARCHER;
                break;
            case 2:
                mSpecialization = Specialization.MAGE;
                break;
        }
    }

    public String[] getRaces() {
        return new String[]{"Human", "Elf", "Orc", "Dwarf"};
    }

    public void setRace(int position) {
        switch (position) {
            case 0:
                mRace = Race.HUMAN;
                break;
            case 1:
                mRace = Race.ELF;
                break;
            case 2:
                mRace = Race.ORC;
                break;
            case 3:
                mRace = Race.DWARF;
                break;
        }
    }

    public String[] getAttributes() {
        return new String[]{"Strength", "Agility", "Intellect", "Stamina", "Luck"};
    }

    public String[] getPerks() {
        return new String[]{"Berserk", "Calm", "Lightweight", "Heavyarmored", "Observant", "Meditations"};

    }

    public void updateAttributeValue(int position, int updateTo) {
        Attribute attribute = null;
        switch (position){
            case 0:
                attribute = Attribute.STRENGTH;
                break;
            case 1:
                attribute = Attribute.AGILITY;
                break;
            case 2:
                attribute = Attribute.INTELLECT;
                break;
            case 3:
                attribute = Attribute.STAMINA;
                break;
            case 4:
                attribute = Attribute.LUCK;
                break;
        }

        int attributeAmount = mAttributesMap.get(attribute.name());

        if (updateTo > 0 && mAvailablePoints >= updateTo || updateTo < 0 && attributeAmount + updateTo > 0) {
            attributeAmount += updateTo;
            mAttributesMap.put(attribute.name(), attributeAmount);
            mAvailablePoints -= updateTo;
            setChanged();
            notifyObservers();
        }
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvailablePoints() {
        return String.valueOf(mAvailablePoints);
    }

    public Map<String, Integer> getAttributesMap() {
        return mAttributesMap;
    }

    public void checkPerk(String text, boolean isChecked) {
        mPerksMap.put(text, isChecked);
    }

    public Character create() {
        Character character = new Character();
        character.setName(mName);
        character.setRace(mRace);
        character.setSpecialization(mSpecialization);
        character.setAttributes(mAttributesMap);
        character.setPerks(mPerksMap);
        character.calculateParameters();
        return character;
    }

    public Specialization getSpecialization() {
        return mSpecialization;
    }

    public Race getRace() {
        return mRace;
    }

    public Map<String, Boolean> getPerksMap() {
        return mPerksMap;
    }

    public void setAvailablePoints(int availablePoints) {
        mAvailablePoints = availablePoints;
    }

    public int getRacePosition() {
        return mRace.ordinal();
    }

    public int getSpecializationPosition() {
        return mSpecialization.ordinal();
    }
}
