import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {SongService} from '../../services/song-service';
import {SongsComponent} from '../songs/songs.component';

@Component({
  selector: 'app-song-create-form',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './song-create-form.component.html',
  styleUrl: './song-create-form.component.css'
})
export class SongCreateFormComponent {
  songCreateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private songService: SongService,
    //private songsComponent: SongsComponent
  ) {
    this.songCreateForm = this.fb.group({
      title: [''],
      releaseYear: [''],
      views: [''],
      genre: [''],
      artistId: ['']
    });
  }

  onSubmit() {this.createSong()}

  createSong() {
    const formData = this.songCreateForm.value;
    this.songService.create(formData)
      .subscribe({
        next: (data: any) => {
          const newSong = data;
          //this.songsComponent.onSongCreated(newSong);
        },
        error: (err: any) => {
          console.log('Error creating song:', err)
        }
      })
  }
}
