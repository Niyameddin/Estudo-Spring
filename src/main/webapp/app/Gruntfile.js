// Gruntfile.js

module.exports = function(grunt) {

  // ===========================================================================
  // CONFIGURE GRUNT ===========================================================
  // ===========================================================================

  grunt.initConfig({

    pkg: grunt.file.readJSON('package.json'),

    	// configure jshint to validate js files -----------------------------------
	    jshint: {
	      options: {
	        reporter: require('jshint-stylish')
	      },
	      
	      build: ['Gruntfile.js', 'assets/src/scripts/**/*.js']
	    },

	    // configure uglify to minify js files -------------------------------------
	    uglify: {
	      options: {
	      	banner: '/*!\n' +
            ' * <%= pkg.name%> v<%= pkg.version %> (<%= pkg.homepage %>)\n' +
            ' * <%= grunt.template.today("dd/mm/yyyy") %> <%= pkg.author %>\n' +
            ' * Licensed under the <%= pkg.license %> license\n' +
            ' */\n',
	        mangle:false
	      },
	      build: {
	        files: {
	          'assets/dist/scripts/scripts.min.js': 'assets/src/scripts/**/*.js'
	        }
	      }
	    },

	    // configure cssmin to minify css files ------------------------------------
	    cssmin: {
	      options: {
	        banner: '/*!\n' +
            ' * <%= pkg.name%> v<%= pkg.version %> (<%= pkg.homepage %>)\n' +
            ' * <%= grunt.template.today("dd/mm/yyyy") %> <%= pkg.author %>\n' +
            ' * Licensed under the <%= pkg.license %> license\n' +
            ' */\n'
	      },
	      build: {
	        files: {
	          'assets/dist/styles/styles.min.css': 'assets/src/styles/**/*.css'
	        }
	      }
	    },

	    // configure to copy files ------------------------------------------
	    copy: {
		  main: {
		    files: [
		      {expand: true, dest: 'assets/dist/images', cwd: 'assets/src/images', src: '**'},
		    ],
		  },
		},

		// configure watch to auto update ------------------------------------------
	    watch: {  
          options: {
              livereload: true
          },

		  stylesheets: { 
			files: ['assets/src/styles/**/*.css'], 
			tasks: ['cssmin'] 
		   },

		  scripts: { 
		    files: 'assets/src/scripts/**/*.js', 
		    tasks: ['jshint', 'uglify'] 
		  },

		  assets: {
		  	files: 'assets/src/images/**',
		  	tasks: ['copy']
		  },

		},

		// configure express server ------------------------------------------
		express: {
          all: {
            options: {
              port: 9000,
              hostname: "localhost",
              bases: [__dirname],
              livereload: true
            }
          }
        },

        // configure to open browser ------------------------------------------
        open: {
          server: {
            path: 'http://localhost:<%= express.all.options.port%>',
          }
        } 
  });

  // ===========================================================================
  // LOAD GRUNT PLUGINS ========================================================
  // ===========================================================================
  
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-express');
  grunt.loadNpmTasks('grunt-open');

  // ============= // CREATE TASKS ========== //

  grunt.registerTask('default', ['jshint', 'uglify', 'cssmin', 'copy', 'express', 'open',  'watch']); 
};