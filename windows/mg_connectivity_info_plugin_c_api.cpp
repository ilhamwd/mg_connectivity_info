#include "include/mg_connectivity_info/mg_connectivity_info_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "mg_connectivity_info_plugin.h"

void MgConnectivityInfoPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  mg_connectivity_info::MgConnectivityInfoPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
