package org.example.interfaces;

import java.util.Date;

public interface IVadeliBankaHesabi extends IBankaHesap{

    void vadeyiUzat(Date yeniVadeTarihi) throws IllegalAccessException;
}
