class StringUtils {

    static removeNonNumbers(value) {
        return value.replace(/[^\d]/g, '');
    }

}
