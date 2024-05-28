import { apiClient } from "../api/ApiClient"

export function addingToken (){
    return apiClient.interceptors.request.use(
        (config) => {
            console.log('intercepting and adding a token')
            config.headers.Authorization = localStorage.getItem('token')
            return config
        }
    )
}
// export const addingToken
//     = () => apiClient.interceptors.request.use(
//     (config) => {
//         console.log('intercepting and adding a token')
//         config.headers.Authorization = localStorage.getItem('token')
//         return config
//     }
// )