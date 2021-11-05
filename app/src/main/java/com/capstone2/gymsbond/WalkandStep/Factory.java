
package com.capstone2.gymsbond.WalkandStep;

import android.content.pm.PackageManager;

import com.capstone2.gymsbond.WalkandStep.services.AbstractStepDetectorService;
import com.capstone2.gymsbond.WalkandStep.services.AccelerometerStepDetectorService;
import com.capstone2.gymsbond.WalkandStep.services.HardwareStepDetectorService;
import com.capstone2.gymsbond.WalkandStep.utils.AndroidVersionHelper;




public class Factory {



    public static Class<? extends AbstractStepDetectorService> getStepDetectorServiceClass(PackageManager pm){
        if(pm != null && AndroidVersionHelper.supportsStepDetector(pm)) {
            return HardwareStepDetectorService.class;
        }else{
            return AccelerometerStepDetectorService.class;
        }
    }
}
