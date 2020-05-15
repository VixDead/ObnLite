package com.vickx.obnlite.Models.DAO;

import com.vickx.obnlite.Models.Dossier;

import java.util.ArrayList;

public class DossierDAO extends DAOBase<Dossier> {
    @Override
    public Dossier getItem(int id) {
        ArrayList<Dossier> dossiers = getSome("id=" + id);
        return dossiers.size()!=0?dossiers.get(0):null;
    }

    @Override
    public ArrayList<Dossier> getAll() {
        ArrayList<Dossier> dossiers = new ArrayList<>();
        for(int i=0; i< 15; i++){
            dossiers.add(new Dossier(i,"Dossier n° " + i*2));
        }
        return dossiers;
    }

    @Override
    public ArrayList<Dossier> getSome(String condition) {
        return null;
    }

    @Override
    public Dossier Insert(Dossier dossier) {
        return dossier;
    }
}
