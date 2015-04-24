
create_sdk_files_prepend () {
    echo 'export PATH=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}:$PATH' >> $script
    echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    echo 'export OE_QMAKE_CC=$CC' >> $script
    echo 'export OE_QMAKE_CXX=$CXX' >> $script
    echo 'export OE_QMAKE_LINK=$CXX' >> $script
    echo 'export OE_QMAKE_AR=$AR' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_HEADERS}' >> $script
    echo 'export OE_QMAKE_MOC=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/moc' >> $script
    echo 'export OE_QMAKE_UIC=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/uic' >> $script
    echo 'export OE_QMAKE_RCC=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/rcc' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qdbuscpp2xml' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qdbusxml2cpp' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
    echo 'export QMAKESPEC=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs/linux-oe-g++' >> $script
    echo 'export QT_CONF_PATH=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qt.conf' >> $script
}

