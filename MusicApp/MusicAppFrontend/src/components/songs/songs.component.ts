import {Component, Input, OnInit} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ISongResponse} from '../../types';
import {SongService} from '../../services/song-service';

@Component({
  selector: 'app-songs',
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
  ],
  templateUrl: './songs.component.html',
  styleUrl: './songs.component.css'
})
export class SongsComponent implements OnInit {
  songs: ISongResponse[] | null = null;
  @Input() artistId!: string;

  constructor(private songService: SongService) {}

  ngOnInit() {
    this.fetchSongs();
  }

  fetchSongs() {
    if (this.artistId != null) {
      this.songService.getArtistSongs(this.artistId)
        .subscribe({
          next: (data: any) => {
            this.songs = data
          },
          error: (err: any) => {
            console.log('Error fetching songs:', err)
          }
        })
    }
  }

  onSongCreated(newSong: ISongResponse) {
    if(this.songs){
      this.songs.push(newSong)
    }
  }

  deleteSong(id: string) {
    this.songService.delete(id)
      .subscribe({
        next: () => {
          this.songs = this.songs?.filter(song => song.id !== id) || null;
        },
        error: (err: any) => {
          console.log('Error deleting song', err);
        }
      })
  }

  onSongUpdated(songId: string | null | undefined, songData: ISongResponse) {
    const editedSong = this.songs?.find(song => song.id === songId)

    if(!editedSong || !songId){
      console.log('Error updating song')
      return;
    }

    Object.assign(
      this.songs?.find(song => song.id === editedSong.id) || {}, songData
    );
  }

}
