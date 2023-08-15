//
//  Generated file. Do not edit.
//

// clang-format off

#include "generated_plugin_registrant.h"

#include <mg_connectivity_info/mg_connectivity_info_plugin.h>

void fl_register_plugins(FlPluginRegistry* registry) {
  g_autoptr(FlPluginRegistrar) mg_connectivity_info_registrar =
      fl_plugin_registry_get_registrar_for_plugin(registry, "MgConnectivityInfoPlugin");
  mg_connectivity_info_plugin_register_with_registrar(mg_connectivity_info_registrar);
}
