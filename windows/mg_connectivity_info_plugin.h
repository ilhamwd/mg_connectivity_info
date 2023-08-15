#ifndef FLUTTER_PLUGIN_MG_CONNECTIVITY_INFO_PLUGIN_H_
#define FLUTTER_PLUGIN_MG_CONNECTIVITY_INFO_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace mg_connectivity_info {

class MgConnectivityInfoPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  MgConnectivityInfoPlugin();

  virtual ~MgConnectivityInfoPlugin();

  // Disallow copy and assign.
  MgConnectivityInfoPlugin(const MgConnectivityInfoPlugin&) = delete;
  MgConnectivityInfoPlugin& operator=(const MgConnectivityInfoPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace mg_connectivity_info

#endif  // FLUTTER_PLUGIN_MG_CONNECTIVITY_INFO_PLUGIN_H_
