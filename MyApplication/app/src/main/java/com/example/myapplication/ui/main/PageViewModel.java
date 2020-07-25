package com.example.myapplication.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            if(input == 1){
                return "People may experience:\n" +
                        "Behavioral: hypervigilance, irritability, or restlessness\n" +
                        "Cognitive: lack of concentration, racing thoughts, or unwanted thoughts\n" +
                        "Whole body: fatigue or sweating\n" +
                        "Also common: anxiety, excessive worry, fear, feeling of impending doom, insomnia, nausea, palpitations, or trembling";
            }else{
                return "Anxiety can seem overwhelming and impossible to control,but there are ways to manage your anxiety that can help you. One quick way to reduce your anxiety is to take a walk outside,allowing you to focus on yourself and not the thoughts in your mind. Breathing exercises\n" +
                        "along with scents can also help you control your anxiety to manageable levels. Long-terms solutions include evading your triggers,writing down your thoughts,and mediation. \n";
            }
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}