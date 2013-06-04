package edu.uoregon.stockdb

class StockMailService {

    def mailService

    def serviceMethod() {

    }

    def sendPasswordReset(Researcher researcher,String randomPassword) {
        mailService.sendMail{
            to "${researcher.username}"
            subject "Your password has been reset on the metagenomics server."
            html "Your password has been reset on the metagenomics server to '${randomPassword}'. Please change"
        }
    }
}
