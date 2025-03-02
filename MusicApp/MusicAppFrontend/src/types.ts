export interface IArtistResponse {
  id: string
  name: string
  surname: string
  age: number
  stagename: string
  popularity: number
}

export interface IArtistRequest {
  name: string
  surname: string
  age: number
  stagename: string
}

export interface ISongResponse {
  id: string
  title: string
  releaseYear: number
  genre: string
  views: number
  artistId: string
}

export interface ISongRequest {
  title: string
  releaseYear: number
  genre: string
  views: number
  artistId: string
}
