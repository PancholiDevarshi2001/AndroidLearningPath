package com.base.hilt.ui.home

import android.Manifest
import android.app.AlertDialog
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.base.ViewModelBase
import com.base.hilt.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : FragmentBase<ViewModelBase, FragmentHomeBinding>() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(true, "Home", true))
    }

    override fun getViewModelClass(): Class<ViewModelBase> = ViewModelBase::class.java


    override fun initializeScreenVariables() {
        observeData()
        getDataBinding().btnNotification.setOnClickListener{
requestNotificationPermission()
        }

        getDataBinding().btn.setOnClickListener{
            findNavController().navigate(R.id.flowApiCallFragment)
        }
        getDataBinding().btn2.setOnClickListener{
            findNavController().navigate(R.id.operatorsFlowFragment)
        }
        getDataBinding().btn3.setOnClickListener{
            findNavController().navigate(R.id.stateSharedFlowFragment)
        }
    }

    private fun observeData() {
    }
    private var systemDialogShown = false

    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                requestLocationPermission()
            } else {
                showPermissionAlertDialog(
                    "Notification Permission Required",
                    "This app requires notification permission for a particular feature to work properly."
                )
            }
        }

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navigateToNextFragment()
            } else {
                showPermissionAlertDialog(
                    "Location Permission Required",
                    "For smooth operation, this app requires location permission."
                )
            }
        }
    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            Toast.makeText(
                requireContext(),
                "Notification Permission Granted.",
                Toast.LENGTH_LONG
            ).show()
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            Toast.makeText(
                requireContext(),
                "Location Permission Granted.",
                Toast.LENGTH_LONG
            ).show()
            navigateToNextFragment()
        }
    }

    private fun navigateToNextFragment() {
        findNavController().navigate(R.id.stateSharedFlowFragment)
    }

    private fun showPermissionAlertDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }

}
//    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//        if (isGranted) {
//
//            Log.e(TAG, "User accepted the notifications!")
//            findNavController().navigate(R.id.stateSharedFlowFragment)
//        } else {
//            // Permission is denied, show a Snackbar or handle accordingly
//            Snackbar.make(
//                getDataBinding().root,
//                "The user denied the notifications ):",
//                Snackbar.LENGTH_LONG
//            )
//                .setAction("Settings") {
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
//                    intent.data = uri
//                    startActivity(intent)
//                }
//                .show()
//        }
//    }

//            when {
//                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) ==
//                        PackageManager.PERMISSION_GRANTED -> {
//                    Log.e(TAG, "User accepted the notifications!")
//                   findNavController().navigate(R.id.stateSharedFlowFragment)
//                }
//                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
//                    Snackbar.make(
//                        getDataBinding().root,
//                        "The user denied the notifications ):",
//                        Snackbar.LENGTH_LONG
//                    )
//                        .setAction("Settings") {
//                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                            val uri: Uri =
//                                Uri.fromParts("com.onesilisondiode.geeksforgeeks", "packageName", null)
//                            intent.data = uri
//                            startActivity(intent)
//                        }
//                        .show()
//                }
//                else -> {
//                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//                }
//            }
//        checkNotificationPermission()
//
//        }
//    private fun checkNotificationPermission() {
//        val notificationManager =
//            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (!notificationManager.isNotificationPolicyAccessGranted) {
//            openNotificationSettings()
//        } else {
//            // Permission is granted, navigate to next screen
//            navigateToNextScreen()
//        }
//    }

//    private fun openNotificationSettings() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val intent = Intent(ACTION_APP_NOTIFICATION_SETTINGS)
//            intent.putExtra(Settings.EXTRA_APP_PACKAGE, requireContext().packageName)
//            startActivityForResult(intent, notificationPermissionRequestCode)
//        } else {
//            val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
//            intent.data = ACTION_APPLICATION_DETAILS_SETTINGS.toUri()
//            intent.data = intent.data!!.buildUpon().appendQueryParameter("package", requireContext().packageName).build()
//            startActivityForResult(intent, notificationPermissionRequestCode)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == notificationPermissionRequestCode) {
//
//            checkNotificationPermission()
//        }
//    }
//
//    private fun navigateToNextScreen() {
//        findNavController().navigate(R.id.stateSharedFlowFragment)
//    }
//    private fun requestRuntimePermission() {
//        if (ActivityCompat.checkSelfPermission(requireContext(), PERMISSION_RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(requireActivity(), "Permission Granted. You can use API which requires the permission.", Toast.LENGTH_LONG).show()
//        } else if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), PERMISSION_RECORD_AUDIO)) {
//            AlertDialog.Builder(requireContext())
//                .setMessage("This app requires RECORD_AUDIO permission for a particular feature to work properly")
//                .setTitle("Permission Required")
//                .setCancelable(false)
//                .setPositiveButton("OK") { dialog, _ ->
//                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(PERMISSION_RECORD_AUDIO), PERMISSION_REQ_CODE)
//                    dialog.dismiss()
//                }
//                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
//                .show()
//        } else {
//            ActivityCompat.requestPermissions(requireActivity(), arrayOf(PERMISSION_RECORD_AUDIO), PERMISSION_REQ_CODE)
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSION_REQ_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(requireActivity(), "Permission Granted. You can use the API which requires the permission.", Toast.LENGTH_LONG).show()
//            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), PERMISSION_RECORD_AUDIO)) {
//                AlertDialog.Builder(requireContext())
//                    .setMessage("This feature is unavailable because it requires permission that you have denied. Please allow microphone (Record Audio) permission from settings to proceed further.")
//                    .setTitle("Permission Required")
//                    .setCancelable(false)
//                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
//                    .setPositiveButton("Settings") { dialog, _ ->
//                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                        val uri = Uri.fromParts("package", "packageName", null)
//                        intent.data = uri
//                        startActivity(intent)
//                        dialog.dismiss()
//                    }
//                    .show()
//            } else {
//                requestRuntimePermission()
//            }
//        }
//    }

//
//    override fun onResume() {
//        super.onResume()
//
//        // Check and request notification permission if needed
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            requestNotificationPermission()
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            requestNotificationPermission()
//        }
//    }