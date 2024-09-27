package com.movtery.pojavzh.ui.fragment.settings

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.movtery.pojavzh.setting.AllSettings
import com.movtery.pojavzh.ui.fragment.CustomMouseFragment
import com.movtery.pojavzh.ui.fragment.FragmentWithAnim
import com.movtery.pojavzh.utils.ZHTools
import fr.spse.gamepad_remapper.Remapper
import net.kdt.pojavlaunch.R
import net.kdt.pojavlaunch.fragments.GamepadMapperFragment

class ControlSettingsFragment(val parent: FragmentWithAnim) : AbstractSettingsFragment(R.layout.settings_fragment_control) {
    private var mainView: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainView = view

        val customControlsCategory = bindCategory(view.findViewById(R.id.custom_controls_category))
        val controlsCategory = bindCategory(view.findViewById(R.id.controls_category))
        val mouseCategory = bindCategory(view.findViewById(R.id.mouse_category))
        val gyroCategory = bindCategory(view.findViewById(R.id.enableGyro_category))
        val controllerCategory = bindCategory(view.findViewById(R.id.controller_category))

        initSwitchView(
            bindSwitchView(
                customControlsCategory,
                "disableGestures",
                AllSettings.disableGestures,
                view.findViewById(R.id.disableGestures_layout),
                R.id.disableGestures_title,
                R.id.disableGestures_summary,
                R.id.disableGestures
            )
        )

        initSwitchView(
            bindSwitchView(
                customControlsCategory,
                "disableDoubleTap",
                AllSettings.disableDoubleTap,
                view.findViewById(R.id.disableDoubleTap_layout),
                R.id.disableDoubleTap_title,
                R.id.disableDoubleTap_summary,
                R.id.disableDoubleTap
            )
        )
        initSeekBarView(
            bindSeekBarView(
                customControlsCategory,
                "timeLongPressTrigger",
                AllSettings.timeLongPressTrigger,
                "ms",
                view.findViewById(R.id.timeLongPressTrigger_layout),
                R.id.timeLongPressTrigger_title,
                R.id.timeLongPressTrigger_summary,
                R.id.timeLongPressTrigger,
                R.id.timeLongPressTrigger_value
            )
        )
        initSeekBarView(
            bindSeekBarView(
                controlsCategory,
                "buttonscale",
                AllSettings.buttonscale,
                "%",
                view.findViewById(R.id.buttonscale_layout),
                R.id.buttonscale_title,
                R.id.buttonscale_summary,
                R.id.buttonscale,
                R.id.buttonscale_value
            )
        )
        initSwitchView(
            bindSwitchView(
                controlsCategory,
                "buttonAllCaps",
                AllSettings.buttonAllCaps,
                view.findViewById(R.id.buttonAllCaps_layout),
                R.id.buttonAllCaps_title,
                R.id.buttonAllCaps_summary,
                R.id.buttonAllCaps
            )
        )
        initSeekBarView(
            bindSeekBarView(
                mouseCategory,
                "mousescale",
                AllSettings.mouseScale,
                "%",
                view.findViewById(R.id.mousescale_layout),
                R.id.mousescale_title,
                R.id.mousescale_summary,
                R.id.mousescale,
                R.id.mousescale_value
            )
        )
        initSeekBarView(
            bindSeekBarView(
                mouseCategory,
                "mousespeed",
                AllSettings.mouseSpeed,
                "%",
                view.findViewById(R.id.mousespeed_layout),
                R.id.mousespeed_title,
                R.id.mousespeed_summary,
                R.id.mousespeed,
                R.id.mousespeed_value
            )
        )
        initSwitchView(
            bindSwitchView(
                mouseCategory,
                "mouse_start",
                AllSettings.virtualMouseStart,
                view.findViewById(R.id.mouse_start_layout),
                R.id.mouse_start_title,
                R.id.mouse_start_summary,
                R.id.mouse_start
            )
        )
        val customMouse = bindView(
            mouseCategory,
            view.findViewById(R.id.zh_custom_mouse_layout),
            R.id.zh_custom_mouse_title,
            R.id.zh_custom_mouse_summary
        )
        customMouse.mainView.setOnClickListener {
            ZHTools.swapFragmentWithAnim(
                parent,
                CustomMouseFragment::class.java,
                CustomMouseFragment.TAG,
                null
            )
        }
        initSwitchView(
            bindSwitchView(
                gyroCategory,
                "enableGyro",
                AllSettings.enableGyro,
                view.findViewById(R.id.enableGyro_layout),
                R.id.enableGyro_title,
                R.id.enableGyro_summary,
                R.id.enableGyro
            )
        )
        initSeekBarView(
            bindSeekBarView(
                gyroCategory,
                "gyroSensitivity",
                (AllSettings.gyroSensitivity * 100).toInt(),
                "%",
                view.findViewById(R.id.gyroSensitivity_layout),
                R.id.gyroSensitivity_title,
                R.id.gyroSensitivity_summary,
                R.id.gyroSensitivity,
                R.id.gyroSensitivity_value
            )
        )
        initSeekBarView(
            bindSeekBarView(
                gyroCategory,
                "gyroSampleRate",
                AllSettings.gyroSampleRate,
                "ms",
                view.findViewById(R.id.gyroSampleRate_layout),
                R.id.gyroSampleRate_title,
                R.id.gyroSampleRate_summary,
                R.id.gyroSampleRate,
                R.id.gyroSampleRate_value
            )
        )
        initSwitchView(
            bindSwitchView(
                gyroCategory,
                "gyroSmoothing",
                AllSettings.gyroSmoothing,
                view.findViewById(R.id.gyroSmoothing_layout),
                R.id.gyroSmoothing_title,
                R.id.gyroSmoothing_summary,
                R.id.gyroSmoothing
            )
        )
        initSwitchView(
            bindSwitchView(
                gyroCategory,
                "gyroInvertX",
                AllSettings.gyroInvertX,
                view.findViewById(R.id.gyroInvertX_layout),
                R.id.gyroInvertX_title,
                R.id.gyroInvertX_summary,
                R.id.gyroInvertX
            )
        )
        initSwitchView(
            bindSwitchView(
                gyroCategory,
                "gyroInvertY",
                AllSettings.gyroInvertY,
                view.findViewById(R.id.gyroInvertY_layout),
                R.id.gyroInvertY_title,
                R.id.gyroInvertY_summary,
                R.id.gyroInvertY
            )
        )
        val changeControllerBindings = bindView(
            controllerCategory,
            view.findViewById(R.id.changeControllerBindings_layout),
            R.id.changeControllerBindings_title,
            R.id.changeControllerBindings_summary
        )
        changeControllerBindings.mainView.setOnClickListener {
            ZHTools.swapFragmentWithAnim(
                parent,
                GamepadMapperFragment::class.java,
                GamepadMapperFragment.TAG,
                null
            )
        }

        val resetControllerBindings = bindView(
            controllerCategory,
            view.findViewById(R.id.resetControllerBindings_layout),
            R.id.resetControllerBindings_title,
            R.id.resetControllerBindings_summary
        )
        resetControllerBindings.mainView.setOnClickListener {
            Remapper.wipePreferences(context)
            Toast.makeText(context, R.string.preference_controller_map_wiped, Toast.LENGTH_SHORT)
                .show()
        }
        initSeekBarView(
            bindSeekBarView(
                controllerCategory,
                "gamepad_deadzone_scale",
                (AllSettings.deadzoneScale * 100F).toInt(),
                "%",
                view.findViewById(R.id.gamepad_deadzone_scale_layout),
                R.id.gamepad_deadzone_scale_title,
                R.id.gamepad_deadzone_scale_summary,
                R.id.gamepad_deadzone_scale,
                R.id.gamepad_deadzone_scale_value
            )
        )

        val mGyroAvailable =
            (requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager).getDefaultSensor(
                Sensor.TYPE_GYROSCOPE
            ) != null
        gyroCategory.setVisibility(mGyroAvailable)

        computeVisibility()
    }

    override fun onSettingsChange() {
        super.onSettingsChange()
        computeVisibility()
    }

    private fun computeVisibility() {
        mainView?.apply {
            setViewVisibility(findViewById(R.id.timeLongPressTrigger_layout), !AllSettings.disableGestures)
            setViewVisibility(findViewById(R.id.gyroSensitivity_layout), AllSettings.enableGyro)
            setViewVisibility(findViewById(R.id.gyroSampleRate_layout), AllSettings.enableGyro)
            setViewVisibility(findViewById(R.id.gyroInvertX_layout), AllSettings.enableGyro)
            setViewVisibility(findViewById(R.id.gyroInvertY_layout), AllSettings.enableGyro)
            setViewVisibility(findViewById(R.id.gyroSmoothing_layout), AllSettings.enableGyro)
        }
    }

    private fun setViewVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}