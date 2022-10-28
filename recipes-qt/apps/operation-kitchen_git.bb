DESCRIPTION = "VersaWware Qt Application"
LICENSE = "CLOSED"

DEPENDS = " \
    qtvirtualkeyboard \
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
    qtquickcontrols2 \
    i2c-tools \
    libgpiod \
"

RDEPENDS_${PN} = " \
    qtvirtualkeyboard \
    default-qt-envs \
    dbus-session \
    libgpiod \
    i2c-tools \
"

SRCBRANCH = "test"
SRCREV = "3fb260dd027ea5921d341a4f5737dc4d1787d374"

# Clone private github repository using ssh
# Requires your public ssh key to be added to your github account
# QT_GIT = "git://git@github.com/jlindberg20/OperationKitchen.git;protocol=ssh"

# Use a local directory
#   $ mkdir ~/versaware
#   $ cd ~/versaware
#   $ git clone git@github.com:jlindberg20/OperationKitchen.git
QT_GIT = "git:///home/${USER}/versaware/OperationKitchen;protocol=file"

SRC_URI = " \
    ${QT_GIT};branch=${SRCBRANCH}; \
    file://operation-kitchen.service \
"

S = "${WORKDIR}/git/VersaWareProject"
PV = "vw-${SRCPV}"

inherit qt6-qmake systemd

do_install_append() {
    install -m 0755 -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/operation-kitchen.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} = "/opt/VersaWareProject/bin/VersaWareProject"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "operation-kitchen.service"
