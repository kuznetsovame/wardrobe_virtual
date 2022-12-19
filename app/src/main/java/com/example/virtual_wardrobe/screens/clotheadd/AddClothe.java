package com.example.virtual_wardrobe.screens.clotheadd;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.virtualwardrobe.databinding.FragmentAddClotheBinding;
import com.example.virtualwardrobe.model.Clothe;
import com.example.virtualwardrobe.screens.MenuActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddClothe extends Fragment {

    private AddClotheViewModel mViewModel;

    public static AddClothe newInstance() {
        return new AddClothe();
    }

    private FragmentAddClotheBinding binding;
    private Uri uri = null;


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == ImagePicker.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            binding.image.setImageURI(data.getData());
            uri = data.getData();
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddClotheBinding.inflate(inflater, container, false);


        binding.btnSave.setOnClickListener(view -> {
            if (uri == null || binding.etName.getText().toString().isEmpty() || binding.etDescription.getText().toString().isEmpty())
                return;

            Clothe clothe = new Clothe();
            clothe.category = binding.type.getItemAtPosition((int) binding.type.getSelectedItemPosition()).toString();
            clothe.name = binding.etName.getText().toString();
            clothe.description = binding.etDescription.getText().toString();
            clothe.image = uri.getPath();

            ((MenuActivity) requireActivity()).clothes.getValue().add(clothe);
            Navigation.findNavController(getView()).popBackStack();
        });

        binding.btnBack.setOnClickListener(view -> {
            getParentFragment().getParentFragmentManager().popBackStack();
        });

        binding.getImage.setOnClickListener(view -> getImage());


        return binding.getRoot();
    }
    private void getImage() {
        ImagePicker.Companion.with(this)
                .galleryOnly()
                .galleryMimeTypes(new String[]{"image/*"})
                .crop()
                .maxResultSize(512, 512)
                .start();
    }


}