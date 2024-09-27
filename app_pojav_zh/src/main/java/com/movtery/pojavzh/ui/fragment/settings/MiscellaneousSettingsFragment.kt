package com.movtery.pojavzh.ui.fragment.settings

import android.os.Bundle
import android.view.View
import com.movtery.pojavzh.setting.AllSettings
import net.kdt.pojavlaunch.LauncherActivity
import net.kdt.pojavlaunch.R
import net.kdt.pojavlaunch.Tools

class MiscellaneousSettingsFragment :
    AbstractSettingsFragment(R.layout.settings_fragment_miscellaneous) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val miscellaneousCategory = bindCategory(view.findViewById(R.id.miscellaneous_category))

        initSwitchView(
            bindSwitchView(
                miscellaneousCategory,
                "arc_capes",
                AllSettings.arcCapes,
                view.findViewById(R.id.arc_capes_layout),
                R.id.arc_capes_title,
                R.id.arc_capes_summary,
                R.id.arc_capes
            )
        )

        initSwitchView(
            bindSwitchView(
                miscellaneousCategory,
                "verifyManifest",
                AllSettings.verifyManifest,
                view.findViewById(R.id.verifyManifest_layout),
                R.id.verifyManifest_title,
                R.id.verifyManifest_summary,
                R.id.verifyManifest
            )
        )

        val zinkPreferSystemDriver = bindSwitchView(
            miscellaneousCategory,
            "zinkPreferSystemDriver",
            AllSettings.zinkPreferSystemDriver,
            view.findViewById(R.id.zinkPreferSystemDriver_layout),
            R.id.zinkPreferSystemDriver_title,
            R.id.zinkPreferSystemDriver_summary,
            R.id.zinkPreferSystemDriver
        )
        if (!Tools.checkVulkanSupport(requireContext().packageManager)) {
            zinkPreferSystemDriver.mainView.visibility = View.GONE
        }
        initSwitchView(zinkPreferSystemDriver)

        initSwitchView(
            bindSwitchView(
                miscellaneousCategory,
                "force_english",
                AllSettings.forceEnglish,
                view.findViewById(R.id.force_english_layout),
                R.id.force_english_title,
                R.id.force_english_summary,
                R.id.force_english
            ).setRequiresReboot(true)
        )

        val notificationPermissionRequest = bindSwitchView(
            miscellaneousCategory,
            "notification_permission_request",
            false,
            view.findViewById(R.id.notification_permission_request_layout),
            R.id.notification_permission_request_title,
            R.id.notification_permission_request_summary,
            R.id.notification_permission_request
        )
        setupNotificationRequestPreference(notificationPermissionRequest)
        initSwitchView(notificationPermissionRequest)
    }

    private fun setupNotificationRequestPreference(notificationPermissionRequest: SettingsViewWrapper) {
        val activity = requireActivity()
        if (activity is LauncherActivity) {
            notificationPermissionRequest.mainView.visibility =
                if (!activity.checkForNotificationPermission()) View.VISIBLE else View.GONE
            notificationPermissionRequest.getSwitchView().setOnCheckedChangeListener { _, _ ->
                activity.askForNotificationPermission {
                    notificationPermissionRequest.mainView.visibility = View.GONE
                }
            }
        } else {
            notificationPermissionRequest.mainView.visibility = View.GONE
        }
    }
}