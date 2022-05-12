package com.example.marvelapp.util

import android.view.LayoutInflater
import android.view.ViewGroup

typealias ActivityInflater<T> = (LayoutInflater) -> T
typealias FragmentInflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T