package com.footprint.android

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class FootprintConfiguration(
    @Transient val redirectActivityName: String? = null,
    @SerialName("public_key") val publicKey: String? = null,
    @SerialName("auth_token") val authToken: String? = null,
    @SerialName("user_data") val bootstrapData: FootprintBootstrapData? = null,
    val options: FootprintOptions? = null,
    val l10n: FootprintL10n? = null,
    @Transient val cloudProjectNumber: Long? = null, // used for generating device attestations
    @Transient val appearance: FootprintAppearance? = null,
    @Transient val onComplete: ((validationToken: String) -> Unit)? = null,
    @Transient val onCancel: (() -> Unit)? = null,
    @Transient val onError: ((errorMessage: String) -> Unit)? = null
) {
    init {
        require((publicKey != null).xor(authToken != null)) {
            "Exactly one of publicKey or authToken must be provided"
        }
    }
}

@Serializable
data class FootprintBootstrapData(
    @SerialName("id.email") val email: String? = null,
    @SerialName("id.phone_number") val phoneNumber: String? = null,
    @SerialName("id.first_name") val firstName: String? = null,
    @SerialName("id.middle_name") val middleName: String? = null,
    @SerialName("id.last_name") val lastName: String? = null,
    @SerialName("id.dob") val dob: String? = null,
    @SerialName("id.address_line1") val addressLine1: String? = null,
    @SerialName("id.address_line2") val addressLine2: String? = null,
    @SerialName("id.city") val city: String? = null,
    @SerialName("id.state") val state: String? = null,
    @SerialName("id.country") val country: String? = null,
    @SerialName("id.zip") val zip: String? = null,
    @SerialName("id.ssn9") val ssn9: String? = null,
    @SerialName("id.ssn4") val ssn4: String? = null,
    @SerialName("id.nationality") val nationality: String? = null,
    @SerialName("id.us_legal_status") val usLegalStatus: String? = null,
    @SerialName("id.citizenships") val citizenships: List<String>? = null,
    @SerialName("id.visa_kind") val visaKind: String? = null,
    @SerialName("id.visa_expiration_date") val visaExpirationDate: String? = null,
    @SerialName("business.address_line1") val businessAddressLine1: String? = null,
    @SerialName("business.address_line2") val businessAddressLine2: String? = null,
    @SerialName("business.beneficial_owners") val businessBeneficialOwners: List<BusinessBeneficialOwners>? = null,
    @SerialName("business.city") val businessCity: String? = null,
    @SerialName("business.corporation_type") val businessCorporationType: String? = null,
    @SerialName("business.country") val businessCountry: String? = null,
    @SerialName("business.dba") val businessDba: String? = null,
    @SerialName("business.formation_date") val businessFormationDate: String? = null,
    @SerialName("business.formation_state") val businessFormationState: String? = null,
    @SerialName("business.kyced_beneficial_owners") val businessKycedBeneficialOwners: List<BusinessBeneficialOwners>? = null,
    @SerialName("business.name") val businessName: String? = null,
    @SerialName("business.phone_number") val businessPhoneNumber: String? = null,
    @SerialName("business.state") val businessState: String? = null,
    @SerialName("business.tin") val businessTin: String? = null,
    @SerialName("business.website") val businessWebsite: String? = null,
    @SerialName("business.zip") val businessZip: String? = null
)

@Serializable
data class BusinessBeneficialOwners(
    @SerialName("email") val boEmail: String? = null,
    @SerialName("first_name") val boFirstName: String? = null,
    @SerialName("last_name") val boLastName: String? = null,
    @SerialName("middle_name") val boMiddleName: String? = null,
    @SerialName("ownership_stake") val boOwnershipStack: Int? = null,
    @SerialName("phone_number") val boPhoneNumber: String? = null
)

@Serializable
data class FootprintOptions(
    @SerialName("show_completion_page") val showCompletionPage: Boolean? = null,
    @SerialName("show_logo") val showLogo: Boolean? = null
)

@Serializable
enum class FootprintSupportedLocale {
    @SerialName("en-US") EN_US,
    @SerialName("es-MX") ES_MX
}

@Serializable
enum class FootprintSupportedLanguage {
    @SerialName("en") ENGLISH,
    @SerialName("es") SPANISH
}

@Serializable
data class FootprintL10n(
    val locale: FootprintSupportedLocale? = null,
    val language: FootprintSupportedLanguage? = null
)